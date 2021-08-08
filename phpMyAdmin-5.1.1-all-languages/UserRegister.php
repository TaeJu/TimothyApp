<?php
  $con = mysqli_connect("localhost", "xown17", "Xlahtl12", "xown17");

  $userID = $_POST["userID"];
  $userPassword = $_POST["userPassword"];
  $userGender = $_POST["userGender"];
  $userSchool = $_POST["userSchool"];
  $userEmail = $_POST["userEmail"];

  $statement = mysqli_prepare($con, "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)");
  mysqli_stmt_bind_param($statement, "sssss", $userID, $userPassword, $userGender, $userSchool, $userEmail);
  mysqli_stmt_execute($statement);

  $response = array();
  $response["success"] = true;

  echo json_encode($response);
?>
