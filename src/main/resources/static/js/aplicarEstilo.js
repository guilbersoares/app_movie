document.addEventListener("DOMContentLoaded", function() {
    var estiloPreferido = localStorage.getItem('estilo');

    if (estiloPreferido) {
        document.getElementById('selEstilo').value = estiloPreferido;
        aplicarEstilo(estiloPreferido);
    }

    function aplicarEstilo(tema) {
        document.getElementById('estilo').href = '/css/' + tema + '.css';
        localStorage.setItem('estilo', tema);
    }
});

