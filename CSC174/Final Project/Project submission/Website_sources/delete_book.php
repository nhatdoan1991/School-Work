<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "csc174";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$isbn = mysqli_real_escape_string($conn, $_REQUEST['isbn']);
// sql to delete a record
$sql = "DELETE FROM ITEMS WHERE isbn='$isbn'";
$sql2 = "DELETE FROM AUTHOR WHERE isbn='$isbn'";


if ($conn->query($sql) === TRUE) {
  echo "Record deleted successfully";
} else {
  echo "Error deleting record: " . $conn->error;
}

if ($conn->query($sql2) === TRUE) {
  echo "Record deleted successfully";
} else {
  echo "Error deleting record: " . $conn->error;
}

$conn->close();
?>
<script type="text/javascript">
setTimeout(function () {
    window.location.href = 'http://nonamecsc174csus.tk/';
},2000); // 5 seconds
</script>

