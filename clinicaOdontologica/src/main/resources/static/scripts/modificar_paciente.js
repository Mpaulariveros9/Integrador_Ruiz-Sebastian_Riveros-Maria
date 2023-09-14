window.addEventListener('load', function () {
    const modificarPacienteForm = document.getElementById("formulario-modificar-paciente");
    const response = document.querySelector('#response');

    modificarPacienteForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const datos = {
            id: document.querySelector("#paciente_id").value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                id: document.querySelector('#domicilio_id').value,
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },
        };

        const url = '/pacientes/actualizar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datos),
        };

        fetch(url, settings)
            .then(response => {
                if (response.ok) {
                    response.innerHTML = '<p>Paciente modificado exitosamente</p>';
                    // Llenar el formulario solo si la solicitud fue exitosa
                    response.querySelector('#paciente_id').value = datos.id;
                    response.querySelector('#nombre').value = datos.nombre;
                    response.querySelector('#apellido').value = datos.apellido;
                    response.querySelector('#dni').value = datos.dni;
                    response.querySelector('#fechaIngreso').value = datos.fechaIngreso;
                } else {
                    throw new Error('Error en la solicitud');
                }
            })
            .catch(error => {
                alert("Error: " + error);
            });
    });

    function findBy(id) {
        const url = '/pacientes/actualizar' + "/" + id;
        const settings = {
            method: 'GET',
        };
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let paciente = data;
                // Actualizar el formulario con los datos del paciente
                document.querySelector('#paciente_id').value = paciente.paciente_id;
                document.querySelector('#nombre').value = paciente.nombre;
                document.querySelector('#apellido').value = paciente.apellido;
                document.querySelector('#dni').value = paciente.dni;
                document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;

                // Mostrar mensaje
                response.style.display = "block";
                response.innerHTML = '<p>Paciente modificado exitosamente</p>';
            })
            .catch(error => {
                alert("Error: " + error);
            });
    }
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
});
