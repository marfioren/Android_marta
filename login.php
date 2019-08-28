<?php


/**
 * Created by PhpStorm.
 * User: Forgift
 * Date: 08/01/2019
 * Time: 16:18
 */

include "Database.php";

$db = new Database();

$username = filter_input(INPUT_GET, "user", FILTER_SANITIZE_STRING);
$pass = filter_input(INPUT_GET, "pass", FILTER_SANITIZE_STRING);

$username = $db->escapeString($username);
$pass = $db->escapeString($pass);


$querry = "SELECT username FROM `User` WHERE username = '$username' and pass = '$pass'";
$result = $db ->executeQuery($querry);
$db->close();
$json = array();

if(mysqli_num_rows($result)>0){
    $json = ["message"=>"Uspjesno logiranje", "success"=>1, "token"=>"random-String21_3"];
}
else{
    $json = ["message"=>"", "success"=>0];
}
echo json_encode($json);

?>