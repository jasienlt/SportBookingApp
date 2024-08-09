<!DOCTYPE html>
<html>
<head>
    <title>File Uploading Form</title>
</head>

<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action = "upload" method = "post"
      enctype = "multipart/form-data">
    <p>
        Description:
        <input type="text" name="description" size="30" required />
    </p>

    <p>
        <input type="file" name="file" required />
    </p>

    <p>
    <br />
    <input type = "submit" value = "Upload File" />
</form>
</body>

</html>