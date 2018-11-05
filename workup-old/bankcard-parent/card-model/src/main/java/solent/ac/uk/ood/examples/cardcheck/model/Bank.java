/* ***************************************************************************
 * Copyright 2018 Craig Gallen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/

package solent.ac.uk.ood.examples.cardcheck.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bank {

    private String name;

    private String sortcode;

    private String bincode;

    private Integer id;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sortcode
     */
    public String getSortcode() {
        return sortcode;
    }

    /**
     * @param sortcode the sortcode to set
     */
    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    /**
     * @return the bincode
     */
    public String getBincode() {
        return bincode;
    }

    /**
     * @param bincode the bincode to set
     */
    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
