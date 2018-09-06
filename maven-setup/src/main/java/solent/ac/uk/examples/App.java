/* ***************************************************************************
 * Copyright 2018 Craig Gallen.
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

package solent.ac.uk.examples;

/**
 * this is a javadoc comment for the hello world application
 * note that javadoc begins with /** not /* which is just in text
 *
 */
public class App {
	public static void main(String[] args) {
		
		App app = new App();
		System.out.println(app.getMessage());
	}
	
	/**
	 * generates a string message
	 * @return String  "Hello World!
	 */
	public String getMessage(){
		return "Hello World!";
	}
}
