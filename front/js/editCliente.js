const URL = "http://localhost:8080/Clientes/webapi/clientes";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); 

window.onload = init;

function init() {
  if (window.location.search != "") {
    const queryStr = window.location.search.substring(1);
    const parametro = queryStr.split("=");
    idcliente = parametro[1];
    console.log(idcliente);

    document.getElementById('titulo').innerHTML= 'Editar Cliente';

    rellenaCliente(idcliente);
  } else {
    document.getElementById("id").value = "Nuevo Cliente";
    document.getElementById("idSalvar").disabled = false;
  }

  
  document.getElementById("idCancel").addEventListener("click", (evt) => {
    evt.preventDefault();
    volver();
  });

  
  document.getElementById("idFormCliente").addEventListener("submit", salvarCliente);
}

function rellenaCliente(idcliente) {
  const peticionHTTP = fetch(URL + "/" + idcliente);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((cliente) => {
      let inputs = document.getElementsByTagName("input");
      for (let input of inputs) {
        input.value = cliente[input.name] ?? "";
      }
      document.getElementById("idSalvar").disabled = false;
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "No he podido recupera este  Cliente " + error, false);
    });
}

function salvarCliente(evt) {
  evt.preventDefault();

  
  let cliente = {};

  let inputs = document.getElementsByTagName("input");
  for (let input of inputs) {
    cliente[input.name] = input.value;
  }

  if (cliente.id == "Nuevo Cliente") { 
    delete cliente.id;
    opciones = {
      method: "POST", 
      body: JSON.stringify(cliente), 
      headers: {
        "Content-Type": "application/json",
      },
    };
  } else { 
    opciones = {
      method: "PUT", 
      body: JSON.stringify(cliente),
      headers: {
        "Content-Type": "application/json",
      },
    };
  }

  fetch(URL, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Fallo al actualizar: " + respuesta);
    })
    .then((respuesta) => {
      muestraMsg("Datos Actualizados", "Todo parace haber ido bien 🎉", false, "success");
    })
    .catch((error) => {
      muestraMsg("Oops..", "No he podido actulizar la Base de Datos 🥺 " + error, false, "error");
    });

  return false;
}

function volver() {
  window.history.back();
}


function muestraMsg(titulo, mensaje, okButton, tipoMsg, okMsg = "OK", closeMsg = "Cerrar") {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo = "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " + titulo;
      }
      break;
    case "question":
      {
        titulo = "<i style='color:blue' class='bi bi-question-circle-fill'></i> " + titulo;
      }
      break;
    default:
      {
        titulo = "<i style='color:green' class='bi bi-check-circle-fill'></i> " + titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton ? "block" : "none";

  myModal.show();
}
