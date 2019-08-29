<?php
/**
 * Created by PhpStorm.
 * User: Forgift
 * Date: 08/01/2019
 * Time: 16:57
 */

class Database
{
    const host = "localhost";

    const base  = "mjerenje_database";
    const user = "mjerenje";
    const pass = "lsXvv1GzW)9X";

    private $connection = null;

    function __construct()
    {
        $this->connection = new mysqli(self::host, self::user, self::pass, self::base, 3306 );

        if ($this->connection->connect_errno)
        {
            echo "Error while connecting : " . $this->connection->connect_errno . ", " . $this->connection->connect_error ."<br>";
        }
        return $this->connection;
    }

    function close ()
    {
        $this->connection->close();
    }

    function executeQuery ($query)
    {
        $result = $this->connection->query($query);

        if ($this->connection->connect_errno)
        {
            echo "Error while connecting : " . $this->connection->connect_errno . ", " . $this->connection->connect_error ."<br>";
            return null;
        }else{
            return $result;
        }
    }

    function escapeString ($string)
    {
        return mysqli_real_escape_string($this->connection, $string);
    }
}