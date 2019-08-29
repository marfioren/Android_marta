<?php

include "Database.php";
include "custom_functions.php";

$db = new Database();



$vlaznost = filter_input(INPUT_GET, "vlaznost", FILTER_SANITIZE_STRING);
$temp = filter_input(INPUT_GET, "temp", FILTER_SANITIZE_STRING);
 $query = "INSERT INTO `dht111`(`humidity`, `temperature`) VALUES ('$vlaznost' ,'$temp')";

$db ->executeQuery($query);
$vlaznost=$vlaznost+1;
$temp=$temp+1;
 $query = "INSERT INTO `dht111`(`humidity`, `temperature`) VALUES ('$vlaznost' ,'$temp')";
$db ->executeQuery($query);
$vlaznost=$vlaznost+1;
$temp=$temp+1;
 $query = "INSERT INTO `dht111`(`humidity`, `temperature`) VALUES ('$vlaznost' ,'$temp')";
$db ->executeQuery($query);

$db->close();

$json = ["message"=>"User don't have access right", "success"=>0];


$db->close();
echo json_encode($json);

?>