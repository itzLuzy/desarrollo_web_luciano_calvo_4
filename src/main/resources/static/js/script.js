function mostrarEvaluacion(td) {
    if (td.textContent == ""){
        return;
    }
    td.onclick = null;
    td.textContent = ""
    let form = document.createElement('form');
    form.id = "form-" + td.id;
    const actId = td.id.replace("eval", "");
    // form.action = "/post-nota/" + actId;
    // form.method = "post";
    let select = document.createElement('select');
    select.id = "select-eval" + actId;
    select.name = "nota";
    for (let i=1; i<=7; i++) {
        let option = document.createElement('option');
        option.value = i;
        option.textContent = i.toString();
        select.appendChild(option);
    }
    let button = document.createElement('button');
    button.type = 'button';
    button.textContent = 'Guardar';
    button.onclick = function(e) {
        e.preventDefault();
        submitNota(actId);
    };
    form.appendChild(select);
    form.appendChild(button);
    td.appendChild(form);
}

function submitNota(id) {
    console.log(id)
    const nota = document.getElementById("select-eval"+id).value.trim();
    fetch("/post-nota/" + id, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "nota=" + encodeURIComponent(nota)
    })
    .then(r => r.json())
    .then(res => {
        if (res.ok) {
            const form = document.getElementById("form-eval"+id);
            const td = form.parentElement;
            form.remove();
            td.textContent = "evaluado!";
            td.class = "";
            updateNotaPromedio(id);
        } 
        else {
            alert("Error al agregar la evaluacion");
        }
    });
}

function updateNotaPromedio(id) {
    fetch('/get-avg/' + id)
        .then(res => res.text())
        .then(prom => {
            const tdNota = document.getElementById("nota" + id);
            tdNota.textContent = prom;
        });
}
