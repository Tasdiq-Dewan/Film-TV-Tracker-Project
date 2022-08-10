function showAllFilms(){
    fetch('/api/films/getAll')
    .then(res => {
        res.json().then(body => {
            displayBody(body);
        })
    })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function displayBody(body){
    const old_tbody = document.getElementById("film-table-body")
    const new_tbody = document.createElement("film-table-body");
    old_tbody.parentNode.replaceChild(new_tbody, old_tbody);
}