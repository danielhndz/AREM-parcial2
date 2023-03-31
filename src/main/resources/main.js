function displayJson(json, div) {
  for (const key of Object.keys(json)) {
    div.innerHTML += key + ": " + json[key] + "<br />";
  }
}

function loadPostMsg(seed) {
  let url = "/collatzsequence?value=" + seed.value;
  fetch(url, { method: "POST" })
    .then((response) => response.json())
    .then((data) => {
      let div = document.getElementById("response");
      div.innerHTML = "";
      displayJson(data, div);
    });
}
