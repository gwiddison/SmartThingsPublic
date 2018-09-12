/**
 *  Fibaro Universal Sensor App (Buttons)
 *
 *  Copyright 2014 Joel Tamkin
 *
 *	2015-10-29: erocm123 - I removed the scheduled refreshes for my Philio PAN04 as it supports instant
 *	status updates with my custom device type
 *  20016-01-25 PukkaHQ - Modified to work with Fibaro Universal Sensor
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Fibaro Univeral Sensor App (Contacts)",
    namespace: "",
    author: "Paul Crookes",
    description: "Associates Dual Relay Switch Modules with one or two standard SmartThings 'switch' devices for compatibility with standard control and automation techniques",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
  section("FUS Module:") {
    input "fus", "capability.contactSensor", title: "Which FUS Module?", multiple: false, required: true
    input "contact1", "capability.contactSensor", title: "First Switch?", multiple: false, required: true
    input "contact2", "capability.contactSensor", title: "Second Switch?", multiple: false, required: true
  }
}

def installed() {
 log.debug "Installed with settings: ${settings}"
 initialize()
}

def updated() {
 log.debug "Updated with settings: ${settings}"
 unsubscribe()
 initialize()
}

def rsmHandler(evt) {
	log.debug "$evt.name $evt.value"
    if (evt.name == "contact1") {
    	switch (evt.value) {
        	case 'open':
            	contact1.open()
                break
            case 'closed':
            	contact1.close()
                break
        }
    }
    else if (evt.name == "contact2") {
    	switch (evt.value) {
        	case 'open':
            	contact2.open()
                break
            case 'closed':
            	contact2.close()
                break
        }
    }
      	
}
  
def initialize() {
 subscribe(fus, "contact1", rsmHandler)
 subscribe(fus, "contact2", rsmHandler)
}