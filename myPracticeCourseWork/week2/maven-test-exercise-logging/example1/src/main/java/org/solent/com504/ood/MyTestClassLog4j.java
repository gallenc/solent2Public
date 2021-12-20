package org.solent.com504.ood;

/*
 * This is not javadoc - usually licence goes here
 */

/**
 * This is a javadoc comment on the Main class
 *
 */

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class MyTestClassLog4j {
   public static Logger logger = LogManager.getLogger(MyTestClassLog4j.class);
		
   public static void main(String[] args) {
       
       // this is a local comment which doesnt go in javadoc
       MyTestClassLog4j myTestClassLog4j = new MyTestClassLog4j();
       myTestClassLog4j.writeAboutMe();
        
   }

 /**
  * This is a javadoc comment on writeAboutMe
  *
  */
  public void writeAboutMe() {
         System.out.println("This is a system out message from : "+ MyTestClassLog4j.class);
         logger.error("This is a log4j message from : "+ MyTestClassLog4j.class);
  }
  
  public String talkAboutMe(String name){
      return "talking about "+name;
  }

}
