<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Raleway" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="content.css" type="text/css">
    <link rel="stylesheet" href="nav.css" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <title>Book Editing</title>
</head>

<body style="background: #E8E8E8;">

    <header>
        <div class="flex-container">
            <nav>

                <ul class="menu">
                    <li class="logo"><a>Edit Book</a></li>
                </ul>

            </nav>
        </div>

    </header>
	<div id="editbook-modal">
		<div class="modal-content">
			<div class="modal-header">
				<span id="close-newgroup" class="close">&times;</span>
				<h2>Edit Book</h2>
			</div>
			<div class="modal-body">
				<form class="form" action="edit_book_request.php" method="POST">
					<fieldset>
<?php
/* Attempt MySQL server connection. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
$link = mysqli_connect("localhost", "root", "", "csc174");

// Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
$sql = "SELECT * FROM ITEMS LEFT JOIN AUTHOR ON ITEMS.ISBN = AUTHOR.ISBN WHERE ITEMS.ISBN = ".$_REQUEST['isbn'];
$result = $link->query($sql);
$row = $result->fetch_assoc();
echo "						<br>
						<label style='padding-bottom:5px;' for='groupname'>ISBN:</label>
						<input type='text'name='isbn' value='".$row['ISBN']."' readonly>
						<br>
						<label style='padding-bottom:5px;' for='groupname'>Tittle:</label>
						<input type='text'name='tittle' value='".$row['TITTLE']."'>
						<br>
						<label style='padding-bottom:5px;'>Edition:</label>
						<input type='text'name='edition' value='".$row['EDITION']."'>
						<br>
						<label style='padding-bottom:5px;'>Author:</label>
						<input type='text'name='author' value='".$row['AUTHOR']."'>
						<br>
						<label style='padding-bottom:5px;'>Publisher:</label>
						<input type='text'name='publisher' value='".$row['PUBLISHER']."'>
						<br>
						<label style='padding-bottom:5px;'>Publish Date:</label>
						<input type='text'name='publish_date' value='".$row['PUBLISH_DATE']."'>
						<br>
					</fieldset>
					<br>
					<button type='submit' class='btn btn-success'>
						<i class='fa fa-check' aria-hidden='true'></i>
						OK
					</button>
					<button id='close' type='button' class='btn btn-danger'>
						<a href='http://nonamecsc174csus.tk/'><i class='fa fa-times' aria-hidden='true'></i>
						Cancel</a>
						</button>";
mysqli_close($link);
?>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>
