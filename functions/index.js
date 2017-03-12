const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);
var db = admin.database();

exports.randomizeUsers = functions.https.onRequest((req, res) => {
  // Grab the text parameter, which is the gift exchange title
  const original = req.query.text;
  var exRef = db.ref("Exchanges/" + original + "/Enrolled Users");

  var count = 0;
  var userIDArr = [];

  exRef.on("child_added", function(snap) {
    count++;
  });

  exRef.once("value", function(snapshot) {
   snapshot.forEach(function(data) {
        userIDArr.push(data.key);
   });
  }, function (errorObject) {
    console.log("The read failed: " + errorObject.code);
  });

  console.log(userIDArr);



  // Push it into the Realtime Database then send a response
  admin.database().ref('/messages').push({original: original}).then(snapshot => {
    // Redirect with 303 SEE OTHER to the URL of the pushed object in the Firebase console.
    res.redirect(303, snapshot.ref);
  });
});

//// Listens for new messages added to /messages/:pushId/original and creates an
//// uppercase version of the message to /messages/:pushId/uppercase
//exports.makeUppercase = functions.database.ref('/messages/{pushId}/original').onWrite(event => {
//      // Grab the current value of what was written to the Realtime Database.
//      const original = event.data.val();
//      console.log('Uppercasing', event.params.pushId, original);
//      const uppercase = original.toUpperCase();
//      // You must return a Promise when performing asynchronous tasks inside a Functions such as
//      // writing to the Firebase Realtime Database.
//      // Setting an "uppercase" sibling in the Realtime Database returns a Promise.
//      return event.data.ref.parent.child('uppercase').set(uppercase);
//    });


