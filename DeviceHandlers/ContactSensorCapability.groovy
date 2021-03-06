/**
 *  XIP Contact Sensor Capability
 *
 *  Copyright 2018 Indu Prakash
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
metadata {
	definition(name: "XIP Contact Sensor Capability", namespace: "induprakash", author: "Indu Prakash") {
		capability "Contact Sensor"
		capability "Health Check"
		command "open"
		command "close"
	}

	tiles {
		standardTile("contact", "device.contact", width: 2, height: 2) {
			state("closed", label: '${name}', icon: "st.contact.contact.closed", backgroundColor: "#00A0DC")
			state("open", label: '${name}', icon: "st.contact.contact.open", backgroundColor: "#e86d13")
		}
		main "contact"
		details "contact"
	}
}

// parse events into attributes
def parse(String description) {
	def pair = description.split(":")
	createEvent(name: pair[0].trim(), value: pair[1].trim())
}

def open() {
	sendEvent(name: "contact", value: "open")
}
def close() {
	sendEvent(name: "contact", value: "closed")
}

def installed() {
	initialize()
}
def updated() {
	initialize()
}
def initialize() {
	sendEvent(name: "DeviceWatch-DeviceStatus", value: "online")
	sendEvent(name: "healthStatus", value: "online")
	sendEvent(name: "DeviceWatch-Enroll", value: [protocol: "cloud", scheme: "untracked"].encodeAsJson(), displayed: false)
}