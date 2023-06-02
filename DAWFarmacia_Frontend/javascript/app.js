function cerrarSesion() {
    sessionStorage.removeItem("mail");
    sessionStorage.removeItem("session");

    window.location.href = "login.html";
}

function nombreUsuario() {
    let doctorName = sessionStorage.getItem("name");

    document.querySelector(".titulo").innerHTML = "Dr. " + doctorName;
}

function getTable() {
    let http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/DAWFarmacia/ServXip?mail=" + mail + "&session=" + session);
    http.send();

    http.onreadystatechange = function () {
        if (http.readyState == 4 && http.status == 200) {
            let tablaXip = http.responseText;

            document.getElementById("tablaXip").innerHTML = tablaXip;
        }
    }
}

function getPatients() {
    let http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/DAWFarmacia/ServePatients?mail=" + mail + "&session=" + session, true);
    http.send();

    http.onreadystatechange = function () {
        if (http.readyState == 4 && http.status == 200) {
            let listaPatients = JSON.parse(http.responseText);
            
            let select = document.getElementById("selectPacientes");


            for (let i = 0; i < listaPatients.length; i++) {
                let option = document.createElement("option");
                option.text = listaPatients[i];
                option.value = listaPatients[i];
                select.add(option);
            }
        }
    }
}

function getMedicines() {
    let http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    
    http.open("GET", "http://localhost:3000/DAWFarmacia/ServeMedicines?mail=" + mail + "&session=" + session, true);
    http.send();

    http.onreadystatechange = function () {
        console.log("Ready state: " + http.readyState);
        console.log("Status: " + http.status);
        if (http.readyState == 4 && http.status == 200) {
            let listaMedicines = JSON.parse(http.responseText);

            let select = document.getElementById("selectMedicinas");

            for (let i = 0; i < listaMedicines.length; i++) {
                let option = document.createElement("option");
                option.text = listaMedicines[i].name;
                option.value = listaMedicines[i].id;
                select.add(option);
            }
        }
    }
}

function enviar() {
    let http = new XMLHttpRequest();

    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    
    let idXip = document.getElementById("idXip").value;
    let mailP = document.getElementById("selectPacientes").value;
    let idMed = document.getElementById("selectMedicinas").value;
    let date = document.getElementById("fechaLimite").value;

    http.open("POST", "http://localhost:3000/DAWFarmacia/Release", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("mail=" + mail + "&session=" + session + "&idXip=" + idXip + "&mailP=" + mailP + "&idMed=" + idMed + "&date=" + date);
    

    http.onreadystatechange = function () {
        if (http.readyState == 4 && http.status == 200) {
            document.querySelector(".alta").innerHTML = http.responseText;
            idXip = "";
            date = "";
        }
    }
}






