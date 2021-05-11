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

$sql = "UPDATE ITEMS 
	SET 
	ISBN='".$_REQUEST['isbn']."',
	TITTLE='".$_REQUEST['tittle']."',
	EDITION='".$_REQUEST['edition']."',
	PUBLISHER='".$_REQUEST['publisher']."',
	PUBLISH_DATE='".$_REQUEST['publish_date']."'
       	WHERE ISBN='".$_REQUEST['isbn']."'";

$sql2 = "UPDATE AUTHOR
	SET
	AUTHOR='".$_REQUEST['author']."' WHERE ISBN='".$_REQUEST['isbn']."'";
if ($conn->query($sql) === TRUE) {
  echo "Record updated successfully";
} else {
  echo "Error updating record: " . $conn->error;
}
if ($conn->query($sql2) === TRUE) {
  echo "Record updated successfully";
} else {
  echo "Error updating record: " . $conn->error;
}

$conn->close();
?>
<script type="text/javascript">
setTimeout(function () {
    window.location.href = 'http://nonamecsc174csus.tk/';
},2000); // 5 seconds
</script>
