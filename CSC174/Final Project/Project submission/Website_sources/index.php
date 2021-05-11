
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Raleway" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="content.css" type="text/css">
    <link rel="stylesheet" href="nav.css" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <title>CSC174 - Final Project - No Name</title>
</head>

<body style="background: #E8E8E8;">

    <header>
        <div class="flex-container">
            <nav>

                <ul class="menu">
                    <li class="logo"><a>CSC174 Final Project - No Names</a></li>
                    <li class="item"><a href="#">Book</a></li>
		    <li class="item"><a href="#">Magazine</a></li>
		    <li class="item"><a href="#">Acedemic_Journal</a></li>

                </ul>

            </nav>
        </div>

    </header>
	<div id="addbook-modal" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span id="close-newgroup" class="close">&times;</span>
				<h2>Create New Book</h2>
			</div>
			<div class="modal-body">
				<form class="form" action="insert_book.php" method="POST">
					<fieldset>
						<legend>New Book</legend>
						<br>
						<label style="padding-bottom:5px;margin-left:100px;">ISBN:</label>
						<input type="text"name="isbn" value="" placeholder="Enter Book's ISBN" required>
						<br>
						<label style="padding-bottom:5px;margin-left:100px;">Tittle:</label>
						<input type="text"name="tittle" value="" placeholder="Enter Book's tittle" required>
						<br>
						<label style="padding-bottom:5px;margin-left:100px;">Edition:</label>
						<input type="text"name="edition" value="" placeholder="Enter edition" required>
						<br>
						<label style="padding-bottom:5px;margin-left:100px;">Author:</label>
						<input type="text"name="author" value="" placeholder="Enter author name" required>
						<br>
						<label style="padding-bottom:5px;margin-left:100px;">Publisher:</label>
						<input type="text"name="publisher" value="" placeholder="Enter publiser" required>
						<br>
						<label style="padding-bottom:5px;margin-left:100px;" for="groupname">Publish Date:</label>
						<input type="text"name="publish_date" value="" placeholder="yyyy-mm-dd" required>
						<br>
					</fieldset>
					<br>
					<button type="submit" class="btn btn-success">
						<i class="fa fa-check" aria-hidden="true"></i>
						OK
					</button>
					<button onclick="document.getElementById('addbook-modal').style.display = 'none';" type="button" class="btn btn-danger">
						<i class="fa fa-times" aria-hidden="true"></i>
						Cancel
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="container content">
		<h2 class="content-heading">Book List</h2>

		<!-- button to create a new custom order -->
    		<button type="button" onclick ="openAddBookModal();"class="btn btn-primary">
			<a><i class="fa fa-plus"></i> New Book</a>
		</button>

		<!-- creating a table to display all orders with status 1/list -->
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

$sql = "SELECT * FROM ITEMS LEFT JOIN AUTHOR ON ITEMS.ISBN = AUTHOR.ISBN WHERE TYPE = 'BOOK'";
$result = $conn->query($sql);
	echo"	<table class='order-list' id='searchTable'> 
			<tr>
				<th>ISBN</th>
				<th>Tittle</th>
				<th>Edition</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>publis Date</th>
				<th>Action</th>
			</tr>	";
if ($result->num_rows > 0) {

	while($row = $result->fetch_assoc()){
	echo"			
			<tr>
				<td>".$row["ISBN"]."</td>
				<td>"
				.$row["TITTLE"].					
				"</td>
				<td>"
				.$row["EDITION"].
				"</td>
				<td>"
				.$row["AUTHOR"].
				"</td>
				<td>"
				.$row["PUBLISHER"].
				"</td>
				<td>"
				.$row["PUBLISH_DATE"].
				"</td>
				<td>

					
					<form style = 'display:inline-block;' action='delete_book.php' method='post'>
					<input type='hidden' name='isbn' value='".$row["ISBN"]."' >
					<button class='btn btn-danger' type='submit'>
						<a><i class='fas fa-trash-alt'></i> Delete</a>
					</button>
					</form>

					<form style = 'display:inline-block;' action='edit_book.php' method='post'>
					<input type='hidden' name='isbn' value='".$row["ISBN"]."' >
					<button class='btn btn-warning' type='submit'>
						<a><i class='fa fa-edit'></i> Edit</a>
					</button>
					</form>
				</td>
			</tr>";
	}echo "</table>";
} else {
  echo "<center><p> 0 results </p></center>";
}
$conn->close();
?>								
			
	</div> 
</body>
<script src="main.js"></script>
</html>
