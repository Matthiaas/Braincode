<!--
 *
 * quick and dirty code!
 * DO NOT USE IN PRODUCTION ENVIRONMENT
 *
 * SECURITY HAZARD
 *
 * you WILL die
 *
-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">


    <title>BrainCode</title>
</head>
<body style = "background-color: #1A1919; color: white">


<?php
$target_dir = "uploads/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));



// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
    echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {

    } else {
        echo "Sorry, there was an error uploading your file.";
    }
}

$t = time();
echo (exec('java -jar RainCode.jar ' . $target_file . ' output'  . $t . '.png'));
echo "<br><a style='color:white' href='out/output" . $t . ".png' download> Download Image in full quality </a>";
?>

</body>
</html>
