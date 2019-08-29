<?php


/**
 * Created by PhpStorm.
 * User: Forgift
 * Date: 08/01/2019
 * Time: 16:18
 */

include "Database.php";

$db = new Database();

$stanica = filter_input(INPUT_GET, "imeStanice", FILTER_SANITIZE_STRING);


$stanica = $db->escapeString($stanica);



$querry = "SELECT avgTemp FROM `HourAverage` WHERE uuid = '$stanica'";
$trenutno = $db ->executeQuery($querry);
$db->close();
        while($row=mysqli_fetch_array($trenutno, MYSQLI_ASSOC))
        {
            $hourStats[] = $row;
        }
       
       
        echo  json_encode($hourStats);

?>