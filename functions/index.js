const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);
var db = admin.database();

exports.randomizeUsers = functions.https.onRequest((req, res) => {
  // Grab the text parameter, which is the gift exchange title
  const original = req.query.text
  var exRef = db.ref("Exchanges/" + original + "/Enrolled Users");


  var count = 0;

  exRef.on("child_added", function(snap) {
    count++;
  });

  var userIDArr = new Array(4);

  var i = 0;
  var j = 0;
  var k = 0;
  exRef.once("value", function(snapshot) {
   snapshot.forEach(function(data) {
        if(i+((userIDArr.length)/3) > userIDArr.length - 1)
        {
            j = i + ((userIDArr.length)/3) - userIDArr.length;
        }
        else
        {
            j = i + (userIDArr.length)/3;
        }
        userIDArr[j] = data.key;
        i++;
   });
   snapshot.forEach(function(data) {
           matchedid = userIDArr[k];

           var matchedUserRef = db.ref("Exchanges/" + original + "/Enrolled Users/");
           matchedUserRef.child(data.key).update({
                   Giftee: matchedid
           });

           k++;
   });
  },
  function (errorObject) {
      console.log("The read failed: " + errorObject.code);
    });



  // Push it into the Realtime Database then send a response
  admin.database().ref('/messages').push({original: original}).then(snapshot => {
    // Redirect with 303 SEE OTHER to the URL of the pushed object in the Firebase console.
    res.redirect(303, snapshot.ref);
  });
});