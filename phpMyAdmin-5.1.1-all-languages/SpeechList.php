<?php
  header("Content-Type: text/html; charset=UTF-8");
  $con = mysqli_connect("localhost", "xown17", "Xlahtl12", "xown17");

  $speechDay = $_GET["speechDay"];
  $speechYear = $_GET["speechYear"];
  $speechEra = $_GET["speechEra"];

  $result = mysqli_query($con, "SELECT * FROM SPEECH WHERE speechDay = '$speechDay' AND speechYear = '$speechYear' AND speechEra = '$speechEra'");
  $response = array();
  while($row = mysqli_fetch_array($result)) {
    array_push($response, array("speechID"=>$row[0], "speechDay"=>$row[1], "speechYear"=>$row[2], "speechEra"=>$row[3], "speechTitle"=>$row[4], "speechPerson"=>$row[5], "speechContent"=>$row[6], "speechLink"=>$row[7]));
  }

  echo json_encode(array("response"=>$response), JSON_UNESCAPED_UNICODE);
  mysqli_close($con);
?>
