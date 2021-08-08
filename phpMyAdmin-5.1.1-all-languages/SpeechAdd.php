<?php
  $con = mysqli_connect("localhost", "xown17", "Xlahtl12", "xown17");

  $userID = $_POST["userID"];
  $speechID = $_POST["speechID"];

  $statement = mysqli_prepare($con, "INSERT INTO USTORAGE VALUES (?, ?)");
  mysqli_stmt_bind_param($statement, "si", $userID, $speechID);
  mysqli_stmt_execute($statement);

  $response = array();
  $response["success"] = true;

  echo json_encode($response);
?>
