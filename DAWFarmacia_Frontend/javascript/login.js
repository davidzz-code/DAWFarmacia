function enviarPOST() {
    var http = new XMLHttpRequest();

    let mail = document.getElementById("mail").value;
    let pass = document.getElementById("pass").value;

    http.open("POST", "http://localhost:3000/Tomcat/Login", true); // Ordenador : puerto / proyecto / clase
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); // Al header "Content type" le envío "application/x-www etc."
    // Dependiendo del archivo que qureamos enviar escribiremos un valor diferente para el "Content-type". 
    // El que está puesto ahora le dice al servidor Tomcat que lea 
    http.send("mail=" + mail + "&pass=" + pass); // Los atributos tienen que enviarse en este formato al usar POST
    


    // RESPUESTA
    // Primero hay que comprobar si han acabado de hablar el front y backend

    // Este es un atributo de la clase XMLHttpRequest.
    // Cuando el readyState llega al valor "4" significará que han llegado a enviarse todos los mensajes.
    http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) { // Si el atributo Status es igual a 200 significa que HA IDO TODO BIEN
            if (http.responseText == "ok") {
                document.getElementById("resultat").innerHTML = "Estás logueado";
            }
        }
    }
}

function enviarLogin() {
    var http = new XMLHttpRequest();
    let mail = document.getElementById("mail").value;
    let pass = document.getElementById("pass").value;
    let name;

    http.open("GET", "http://localhost:3000/DAWFarmacia/Login?mail="+mail+"&pass="+pass, true); // Ordenador : puerto / proyecto / clase
    http.send();
    
    // RESPUESTA
    // Primero hay que comprobar si han acabado de hablar el front y backend

    // "readyState" es un atributo de la clase XMLHttpRequest.
    // Va incrementando su valor de 0 a 4 mientras se conecta al servidor
    // Cuando el readyState llega al valor "4" significará que han llegado a enviarse todos los mensajes.
    http.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) { // Si el atributo Status es igual a 200 significa que HA IDO TODO BIEN
            
            // Después de comprobar que readyState ha llegado a 4 y Status a 200. 
            // Miramos que el resposeText sea diferente a 0. Hacerlo antes del readyState no tendría sentido
            let sessionName = this.responseText
            let valores = sessionName.split(";");

            let session = valores[0];
            let doctorName = valores[1];

            if (session != false) {
                window.sessionStorage.setItem("mail", mail);
                window.sessionStorage.setItem("session", session);
                window.sessionStorage.setItem("name", doctorName);
                window.location.href = "gestion.html";
            } else {
                document.getElementById("resultat").innerHTML = "Login incorrecto";
            }
        }
    }
}