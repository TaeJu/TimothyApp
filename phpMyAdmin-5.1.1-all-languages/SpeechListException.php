<?php
header("Content-Type: text/html; charset=UTF-8");
$con = mysqli_connect("localhost", "xown17", "Xlahtl12", "xown17");

$userID = $_GET["userID"];

$result = mysqli_query($con, "SELECT SPEECH.speechID, SPEECH.speechYear, SPEECH.speechPerson FROM USERS, SPEECH, USTORAGE WHERE USERS.userID = '$userID' AND USERS.userID = USTORAGE.userID AND USTORAGE.speechID = SPEECH.speechID");
$response = array();
while($row = mysqli_fetch_array($result)) {
  array_push($response, array("speechID"=>$row[0], "speechYear"=>$row[1], "speechPerson"=>$row[2]));
}

echo json_encode(array("response"=>$response), JSON_UNESCAPED_UNICODE);
mysqli_close($con);
?>
