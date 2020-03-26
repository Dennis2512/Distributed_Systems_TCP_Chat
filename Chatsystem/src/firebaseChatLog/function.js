
import * as configuration from './firebaseConfig.js';
var firebase = require("firebase/app");
require("firebase/database");

const config = configuration.config;
firebase.initializeApp(config);

/* export */ function doAll (message, sender_name, partner_name) {
  console.log("Skript erreicht");
  saveMessageSender();
  saveMessagePartner();
}

// Save chat for sender
function saveMessageSender() {
  firebase.database().ref('chatlog/' + sender_name + "_" + partner_name).append({
      //time: timeStamp,
      from: sender_name,
      to: partner_name,
      msg : message
    });
}

// Save chat for partner
function saveMessagePartner() {
  firebase.database().ref('chatlog/' + partner_name + "_" + sender_name).append({
      //time: timeStamp,
      from: sender_name,
      to: partner_name,
      msg : message
    });
}