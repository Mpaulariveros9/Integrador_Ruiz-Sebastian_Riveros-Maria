window.addEventListener('load', function () {
    const formulario = document.getElementById('registroPacienteForm');
    const pacienteRegistrado = document.querySelector('#pacienteRegistrado');
    
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio:{
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
                }
        };

        /*Usamos la función fetch la API con el método POST, el cual, guardará
        el paciente que enviaremos en formato JSON*/
        const url = 'pacientes/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 document.querySelector('#pacienteRegistrado').style.display = "block";
                 pacienteRegistrado.innerHTML = '<p>Se registro correctamente</p>';
                 resetUploadForm();

            })
            .catch(error => {
             document.querySelector('#pacienteRegistrado').style.display = "block";
                    pacienteRegistrado.innerHTML = '<p>No se pude hacer el registro</p>' ;
             resetUploadForm();
            })
    });

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }
})