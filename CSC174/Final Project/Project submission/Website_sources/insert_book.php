<?php
/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$link = mysqli_connect("localhost", "root", "", "csc174");
 
// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
 
// Escape user inputs for security
$isbn = mysqli_real_escape_string($link, $_REQUEST['isbn']);
$tittle = mysqli_real_escape_string($link, $_REQUEST['tittle']);
$edition = mysqli_real_escape_string($link, $_REQUEST['edition']);
$author = mysqli_real_escape_string($link, $_REQUEST['author']);
$publisher = mysqli_real_escape_string($link, $_REQUEST['publisher']);
$publish_date = mysqli_real_escape_string($link, $_REQUEST['publish_date']);

if (preg_match("/^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/",$publish_date)) {
  
} else {
     die("ERROR: Wrong date type.". mysqli_error($link));

}

// Attempt insert query execution
$sql = "INSERT INTO ITEMS (ISBN,PUBLISH_DATE , TITTLE,PUBLISHER,EDITION,TYPE) VALUES ('$isbn', '$publish_date', '$tittle','$publisher','$edition','BOOK');";
$sql2 =	"INSERT INTO AUTHOR(ISBN,AUTHOR) VALUES ('$isbn','$author');";
if(mysqli_query($link, $sql)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}
if(mysqli_query($link, $sql2)){
    echo "Records added successfully.";
} else{
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
}
 
// Close connection
mysqli_close($link);
?>
<script type="text/javascript">
setTimeout(function () {    
    window.location.href = 'http://nonamecsc174csus.tk/'; 
},2000); // 5 seconds
</script>
