<?php 
include "Database.php";

        $database =  new Database();
        $hourStats = array();
        $escString = $database->escapeString($station);

        $query = "SELECT humidity FROM dht111 WHERE ID = (SELECT MAX(ID) FROM dht111)";
        $trenutno = $database ->executeQuery($query);
        $database->close();

        while($row=mysqli_fetch_array($trenutno, MYSQLI_ASSOC))
        {
            $hourStats[] = $row;
        }
        $json['data'] = $hourStats;
        $json['type'] = "current";
        return  json_encode($json);
?>