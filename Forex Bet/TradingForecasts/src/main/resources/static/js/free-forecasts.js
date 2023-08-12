function reloadForecasts() {

    let forecastContainer = document.getElementById('forecast-container')
    let forecastTable = document.createElement("table")
    forecastContainer.innerHTML = ''

    fetch(`http://localhost:8080/api/free-forecasts`)
        .then(response => response.json())
        .then(json => json.forEach(forecast => {

            let row = document.createElement('tr')

            let categoryCol = document.createElement('td')
            let typeCol = document.createElement('td')
            let dateCreated = document.createElement('td')
            let pictureCol = document.createElement('td')
            let img = document.createElement("img")
            img.setAttribute("src", forecast.pictureUrl)
            let descriptionCol = document.createElement('td')

            categoryCol.textContent = forecast.category.category
            typeCol.textContent = forecast.forecastType
            dateCreated.textContent = forecast.created
            pictureCol.appendChild(img)
            descriptionCol.textContent = forecast.description

            row.appendChild(categoryCol)
            row.appendChild(typeCol)
            row.appendChild(dateCreated)
            row.appendChild(pictureCol)
            row.appendChild(descriptionCol)

            forecastTable.appendChild(row)
        }))

    forecastContainer.appendChild(forecastTable)
}