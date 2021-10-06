package org.solent.com504.project.impl.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.impl.validator.UserValidator;
import org.solent.com504.project.model.party.dto.Address;
import org.solent.com504.project.model.party.dto.Party;
import org.solent.com504.project.model.party.dto.PartyRole;
import org.solent.com504.project.model.party.service.PartyService;
import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.model.user.dto.UserRoles;
import org.solent.com504.project.model.user.service.SecurityService;
import org.solent.com504.project.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class UserController {

    final static Logger LOG = LogManager.getLogger(UserController.class);

    {
        LOG.debug("UserController created");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PartyService partyService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.create(userForm);

        // if not logged in then log in as new party
        // if logged in, stay as present party (e.g. global admin)
        if (!hasRole(UserRoles.ROLE_USER.name())) {
            LOG.debug("creating new user and logging in : " + userForm);
            securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        } else {
            LOG.debug("creating new user : " + userForm);
        }

        return "redirect:/viewModifyUser?username=" + userForm.getUsername();
    }

    @RequestMapping(value = "/denied", method = {RequestMethod.GET, RequestMethod.POST})
    public String denied(Model model) {
        return "denied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }
    
    // this redirects calls to the root of our application to index.html
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contact(Model model) {
        return "contact";
    }

    @RequestMapping(value = {"/users"}, method = RequestMethod.GET)
    public String users(Model model) {
        List<User> userList = userService.findAll();

        LOG.debug("users called:");
        for (User user : userList) {
            LOG.debug(" user:" + user);
        }

        model.addAttribute("userListSize", userList.size());
        model.addAttribute("userList", userList);

        return "users";
    }

    @RequestMapping(value = {"/viewModifyUser"}, method = RequestMethod.GET)
    public String modifyuser(Model model,
            @RequestParam(value = "username", required = true) String username, Authentication authentication) {

        // security check if party is allowed to access or modify this party
        if (!hasRole(UserRoles.ROLE_GLOBAL_ADMIN.name())) {
            if (!username.equals(authentication.getName())) {
                LOG.warn("security warning without permissions, modifyuser called for username=" + username);
                return ("denied");
            }
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            LOG.warn("security warning modifyuser called for unknown username=" + username);
            return ("denied");
        }

        LOG.debug("viewUser called for username=" + username + " user=" + user);
        model.addAttribute("user", user);

        Map<String, String> selectedRolesMap = selectedRolesMap(user);

        for (Entry entry : selectedRolesMap.entrySet()) {
            LOG.debug(username + " role:" + entry.getKey() + " selected:" + entry.getValue());
        }

        model.addAttribute("selectedRolesMap", selectedRolesMap);

        return "viewModifyUser";
    }

    @RequestMapping(value = {"/viewModifyUser"}, method = RequestMethod.POST)
    public String updateuser(Model model,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "selectedRoles", required = false) List<String> selectedRolesIn,
            @RequestParam(value = "userEnabled", required = false) String userEnabled,
            @RequestParam(value = "number", required = false) String number,
            @RequestParam(value = "addressLine1", required = false) String addressLine1,
            @RequestParam(value = "addressLine2", required = false) String addressLine2,
            @RequestParam(value = "county", required = false) String county,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "postcode", required = false) String postcode,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude,
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "mobile", required = false) String mobile,
            Authentication authentication
    ) {
        LOG.debug("updateUser called for username=" + username);

        // security check if party is allowed to access or modify this party
        if (!hasRole(UserRoles.ROLE_GLOBAL_ADMIN.name())) {
            if (!username.equals(authentication.getName())) {
                LOG.warn("security warning without permissions, updateUser called for username=" + username);
                return ("denied");
            }
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            LOG.warn("security warning updateUser called for unknown username=" + username);
            return ("denied");
        }

        String errorMessage = "";

        if (firstName != null) {
            user.setFirstName(firstName);
        }
        if (secondName != null) {
            user.setSecondName(secondName);
        }
        if (userEnabled != null) {
            user.setEnabled(Boolean.TRUE);
        } else {
            user.setEnabled(Boolean.FALSE);
        }

        Address address = new Address();
        address.setNumber(number);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCountry(country);
        address.setCounty(county);
        address.setPostcode(postcode);
        address.setMobile(mobile);
        address.setTelephone(telephone);
        try {
            address.setLatitude(Double.parseDouble(latitude));
            address.setLongitude(Double.parseDouble(longitude));
        } catch (Exception ex) {
            errorMessage = "problem parsing latitude=" + latitude
                    + " or longitude=" + longitude;
        }
        user.setAddress(address);

        user = userService.save(user);

        // update roles if roles in list
        if (selectedRolesIn != null) {
            user = userService.updateUserRoles(username, selectedRolesIn);
        }

        Map<String, String> selectedRolesMap = selectedRolesMap(user);

        model.addAttribute("user", user);

        model.addAttribute("selectedRolesMap", selectedRolesMap);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", "User " + user.getUsername() + " updated successfully");

        return "viewModifyUser";
    }

    private Map<String, String> selectedRolesMap(User user) {

        List<String> availableRoles = userService.getAvailableUserRoleNames();

        List<String> selectedRoles = new ArrayList();
        for (Role role : user.getRoles()) {
            selectedRoles.add(role.getName());
            LOG.debug("user " + user.toString()
                    + "roles from database:" + role.getName());
        }

        Map<String, String> selectedRolesMap = new LinkedHashMap();
        for (String availableRole : availableRoles) {
            if (selectedRoles.contains(availableRole)) {
                selectedRolesMap.put(availableRole, "checked");
                LOG.debug("availableRole " + availableRole
                        + " user " + user.toString() + " available role:checked");
            } else {
                selectedRolesMap.put(availableRole, "");
                LOG.debug("availableRole " + availableRole
                        + " user " + user.toString() + " available role:not checked");
            }
        }

        return selectedRolesMap;

    }

    /**
     * returns true if the party has the role specified
     *
     * @param role
     * @return
     */
    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities
                = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    // PARTY MANAGEMENT
    @RequestMapping(value = {"/partys"}, method = RequestMethod.GET)
    public String partys(Model model) {

        LOG.debug("partys called:");
        List<Party> partyList = partyService.findAll();

        for (Party party : partyList) {
            LOG.debug(" party:" + party + " users.size="
                    + ((party.getUsers() == null) ? "null" : party.getUsers().size()));
        }

        model.addAttribute("partyListSize", partyList.size());
        model.addAttribute("partyList", partyList);

        return "partys";
    }

    @RequestMapping(value = {"/partys"}, method = RequestMethod.POST)
    @Transactional
    public String changePartys(Model model) {

        LOG.debug("partys POST called:");
        List<Party> partyList = partyService.findAll();

        for (Party party : partyList) {
            LOG.debug(" party:" + party + " users.size="
                    + ((party.getUsers() == null) ? "null" : party.getUsers().size()));
        }

        model.addAttribute("partyListSize", partyList.size());
        model.addAttribute("partyList", partyList);

        return "partys";
    }

    @RequestMapping(value = {"/viewModifyParty"}, method = RequestMethod.GET)
    public String reviewParty(Model model,
            @RequestParam(value = "partyuuid") String partyuuid, Authentication authentication) {

        Party party = null;

        LOG.debug("viewModifyParty GET called for partyuuid=" + partyuuid);
        party = partyService.findByUuid(partyuuid);
        if (party == null) {
            LOG.warn("security warning modifyparty called for unknown partyuuid=" + partyuuid);
            return ("denied");
        }

        // security check if party is allowed to access or modify this party
//        if (!hasRole(UserRoles.ROLE_GLOBAL_ADMIN.name())) {
//            if (!partyuuid.equals(authentication.getName())) {
//                LOG.warn("security warning without permissions, modifyuser called for partyuuid=" + partyuuid);
//                return ("denied");
//            }
//        }
        LOG.debug("viewModifyParty GET called for uuid=" + partyuuid + " party=" + party);
        model.addAttribute("party", party);

        // find selected party role
        List<PartyRole> availablePartyRoles = partyService.getAvailablePartyRoles();
        Map<String, String> availablePartyRolesMap = new LinkedHashMap();
        for (PartyRole prole : availablePartyRoles) {
            availablePartyRolesMap.put(prole.name(), ((prole.equals(party.getPartyRole()) ? "selected" : "")));
        }
        model.addAttribute("availablePartyRolesMap", availablePartyRolesMap);

        Map<String, String> selectedRolesMap = new HashMap(); // = selectedRolesMap(party);
        //for (Entry entry : selectedRolesMap.entrySet()) {
        //   LOG.debug(partyuuid + " role:" + entry.getKey() + " selected:" + entry.getValue());
        // }
        model.addAttribute("selectedUsersMap", selectedRolesMap);
        return "viewModifyParty";
    }

    @RequestMapping(value = {"/viewModifyParty"}, method = RequestMethod.POST)
    public String updateParty(Model model,
            @RequestParam(value = "partyuuid", required = false) String partyuuid,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "secondName", required = false) String secondName,
            @RequestParam(value = "partyRole", required = false) String partyRole,
            @RequestParam(value = "partyEnabled", required = false) String partyEnabled,
            @RequestParam(value = "number", required = false) String number,
            @RequestParam(value = "addressLine1", required = false) String addressLine1,
            @RequestParam(value = "addressLine2", required = false) String addressLine2,
            @RequestParam(value = "county", required = false) String county,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "postcode", required = false) String postcode,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude,
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "removeUsername", required = false) String removeUsername,
            @RequestParam(value = "addUsers", required = false) List<String> addUsers,
            Authentication authentication) {

        LOG.debug("viewModifyParty POST called for partyuuid=" + partyuuid);
        String errorMessage = "";

        // security check if user is allowed to access or modify this party
        if (!hasRole(UserRoles.ROLE_GLOBAL_ADMIN.name())) {
            //     if (!partyuuid.equals(authentication.getName())) {
            LOG.warn("security warning without permissions, viewModifyParty called for username=" + partyuuid);
            return ("denied");
            //     }
        }

        Party party = null;

        // If partyuuid is null or empty in a post assume create new party
        if (partyuuid == null || partyuuid.isEmpty()) {
            party = new Party();
            LOG.debug("viewModifyParty POST called to create Party uuid=" + party.getUuid());
            
            // else try to modify an existing party    
        } else {
            party = partyService.findByUuid(partyuuid);
            if (party == null) {
                LOG.warn("security warning viewModifyParty POST called for unknown partyuuid=" + partyuuid);
                return ("denied");
            }

            // add user if requested
            if (addUsers != null) {
                for (String username : addUsers) {
                    User user = userService.findByUsername(username);
                    if (user != null) {
                        party.addUser(user);
                    }
                    LOG.debug("adding username" + username + " user " + user
                            + " to party " + party);
                }
                // remove user if requested
            } else if (removeUsername != null ) {
                LOG.debug("removing username=" + removeUsername + " from party " + party);
                Iterator<User> users = party.getUsers().iterator();
                while (users.hasNext()) {
                    User user = users.next();
                    if (removeUsername.equals(user.getUsername())) {
                        party.removeUser(user);
                        LOG.debug("removing username" + removeUsername + " user " + user
                                + " from party " + party);
                        break;
                    }
                }

            } else { // update values if not adding / removing user
                LOG.debug("updating party partyuuid="+partyuuid);
                party.setUuid(partyuuid);

                if (partyRole != null) {
                    try {
                        party.setPartyRole(PartyRole.valueOf(partyRole));
                    } catch (IllegalArgumentException ex) {
                        LOG.error("update pary used unknown partyRole" + partyRole);
                    }
                }

                if (firstName != null) {
                    party.setFirstName(firstName);
                }
                if (secondName != null) {
                    party.setSecondName(secondName);
                }
                if (partyEnabled != null && "true".equals(partyEnabled)) {
                    party.setEnabled(Boolean.TRUE);
                } else {
                    party.setEnabled(Boolean.FALSE);
                }

                Address address = new Address();
                address.setNumber(number);
                address.setAddressLine1(addressLine1);
                address.setAddressLine2(addressLine2);
                address.setCountry(country);
                address.setCounty(county);
                address.setPostcode(postcode);
                address.setMobile(mobile);
                address.setTelephone(telephone);
                try {
                    address.setLatitude(Double.parseDouble(latitude));
                    address.setLongitude(Double.parseDouble(longitude));
                } catch (Exception ex) {
                    errorMessage = "problem parsing latitude=" + latitude
                            + " or longitude=" + longitude;
                }
                party.setAddress(address);
            }

        }

        // find selected party role
        List<PartyRole> availablePartyRoles = partyService.getAvailablePartyRoles();
        Map<String, String> availablePartyRolesMap = new LinkedHashMap();
        for (PartyRole prole : availablePartyRoles) {
            availablePartyRolesMap.put(prole.name(), ((prole.equals(party.getPartyRole()) ? "selected" : "")));
        }
        model.addAttribute("availablePartyRolesMap", availablePartyRolesMap);

        party = partyService.save(party);

        // update roles if roles in list
        // if (selectedRolesIn != null) {
        //     party = partyService.updateUserRoles(partyuuid, selectedRolesIn);
        // }
        // Map<String, String> selectedRolesMap = selectedRolesMap(party);
        model.addAttribute("party", party);

        Map<String, String> selectedRolesMap = new HashMap(); // = selectedRolesMap(party);
        //for (Entry entry : selectedRolesMap.entrySet()) {
        //   LOG.debug(uuid + " role:" + entry.getKey() + " selected:" + entry.getValue());
        // }
        model.addAttribute("selectedUsersMap", selectedRolesMap);

        // add message if there are any 
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("message", "Party " + party.getUuid() + " updated successfully");

        return "viewModifyParty";
    }
    

    @RequestMapping(value = {"/addUsersToParty"}, method = RequestMethod.POST)
    public String addUsersToParty(Model model,
            @RequestParam(value = "partyuuid", required = false) String partyuuid
    ) {
        List<User> userList = userService.findAll();

        LOG.debug("addUsersToParty called:");
        for (User user : userList) {
            LOG.debug(" user:" + user);
        }

        model.addAttribute("userListSize", userList.size());
        model.addAttribute("userList", userList);
        model.addAttribute("partyuuid", partyuuid);

        return "addUsersToParty";
    }

}
