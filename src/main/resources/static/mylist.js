function showAllEntries(){
    fetch('/api/list/getAll')
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body); 
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function displayAllInBody(res){
    let tbody = document.getElementById("mylist-table-body")
    tbody.innerHTML = "";
    res.forEach(show => {
        tbody.appendChild(populateRow(show));
    });
}

function populateRow(entry){
    let table = document.getElementById("mylist-table-body");
    let row = document.createElement("tr");

    let id = document.createElement("th");
    id.scope = "row";
    id.innerHTML = `${entry.id}`;
    row.appendChild(id);

    let name = document.createElement("td");
    name.innerHTML = `${entry.name}`;
    row.appendChild(name);
    
    let status = document.createElement("td");
    status.innerHTML = `${entry.status}`;
    row.appendChild(status);

    let total = document.createElement("td");
    total.innerHTML = `${entry.total}`;
    row.appendChild(total);

    let progress = document.createElement("td");
    progress.innerHTML = `${entry.progress}`;
    row.appendChild(progress);

    let genre = document.createElement("td");
    genre.innerHTML = `${entry.genre}`;
    row.appendChild(genre);

    let rating = document.createElement("td");
    rating.innerHTML = `${entry.rating}`;
    row.appendChild(rating);

    return row;
}

async function getEntry(id){
    const res = await fetch(`/api/list/getEntry/${id}`);

    return await res.json();
    
}

async function updateEntry(){
    let id = document.getElementById("upId").value;
    let entry = await getEntry(id);
    let status = document.getElementById("upstatus").value;
    let progress = entry.progress;
    if(status === "Complete"){
        progress = entry.total;
    }
    else if(document.getElementById("upprogress").value <= entry.total){
        progress = document.getElementById("upprogress").value;
    }

    if(progress === entry.total){
        status = "Complete";
    }

    let rating = document.getElementById("uprating").value;

    fetch(`/api/list/update/${id}`, {
        method: "PUT",
        body: JSON.stringify({
            "id": id,
            "name": entry.name,
            "filmId": entry.filmId,
            "showId": entry.showId,
            "genre": entry.genre,
            "total": entry.total,
            "status": status,
            "progress": progress,
            "rating": rating
        }),
        headers: {
            "Content-Type": "application/json"
        }
    } 
    ).then(res => {
        res.json().then(body => {
                //data = JSON.stringify(body);
                console.log(body);
        });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })

}

function openUpdateForm() {
    document.getElementById("updateEntryForm").style.display = "block";
}
  
function closeUpdateForm() {
    document.getElementById("updateEntryForm").style.display = "none";
}

function deleteEntry(){
    let id = document.getElementById("delId").value;
    
    fetch(`/api/list/delete/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        } 
    ).then(res => {
        res.json().then(body => {
                //data = JSON.stringify(body);
                console.log(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openDeleteForm() {
    document.getElementById("deleteEntryForm").style.display = "block";
}
  
function closeDeleteForm() {
    document.getElementById("deleteEntryForm").style.display = "none";
}

function searchEntry(){
    let name = document.getElementById("searchname").value;
    fetch(`/api/list/search/${name}`, {method: "GET"})
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openSearchForm() {
    document.getElementById("searchEntryForm").style.display = "block";
}
  
function closeSearchForm() {
    document.getElementById("searchEntryForm").style.display = "none";
}

function filterWatchList(){
    let status = document.getElementById("filterstatus").value;
    let genre = document.getElementById("filtergenre").value;
    let rating = document.getElementById("filterrating").value;
    fetch(`/api/list/searchBy?status=${status}&genre=${genre}&rating=${rating}`, {method: "GET"})
    .then(res => {
        res.json().then(body => {
                data = JSON.stringify(body);
                console.log(data);
                displayAllInBody(body);
           });
        })
    .catch(err =>{
        console.log(err);
        document.body.innerHTML= `<p>Error: ${err.message}</p>`;
    })
}

function openFilterForm() {
    document.getElementById("filterListForm").style.display = "block";
}
  
function closeFilterForm() {
    document.getElementById("filterListForm").style.display = "none";
}