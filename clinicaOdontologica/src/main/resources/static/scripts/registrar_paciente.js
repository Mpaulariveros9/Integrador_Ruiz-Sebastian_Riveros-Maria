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
                 pacienteRegistrado.innerHTML = '<p>Paciente registrado exitosamente</p>';
                 resetUploadForm();

            })
            .catch(error => {
             document.querySelector('#pacienteRegistrado').style.display = "block";
                    pacienteRegistrado.innerHTML = '<p>Paciente no se pudo registrar</p>' ;
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