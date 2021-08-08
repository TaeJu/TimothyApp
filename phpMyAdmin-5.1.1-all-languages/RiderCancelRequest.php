<?php
  $con = mysqli_connect("localhost", "xown17", "Xlahtl12", "xown17");

  $userID = $_POST["userID"];

  $response["success"] = false;

  $sql = "UPDATE USERS SET userRiderStatus = false WHERE userID = '$userID'";

  if (mysqli_query($con, $sql)) {
    $response["success"] = true;
  }

  echo json_encode($response);
?>
