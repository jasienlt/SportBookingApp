function checkUniqueness() {
    var xmlhttp = new XMLHttpRequest();
    var username = document.forms["court-register"]["name"].value;
    var url = "/admin/court/register?username=" + username;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText.includes("NO")) {
                document.getElementById("eMsg").innerHTML = "Username already exists";
            }
        }
    };
    try {
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } catch (e) {
        alert("unable to connect to server");
    }
}

