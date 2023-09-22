var gold_price = 0


document.addEventListener('DOMContentLoaded', () => {
    fetch("https://data.nasdaq.com/api/v3/datasets/LBMA/GOLD/data.json?api_key=rERAPrPQm1mA9RrCYUkK&limit=1")
    .then(response => {
    return response.json();
    })
    .then(json => {
    gold_price = json.dataset_data.data[0][1];
    let old_string = document.getElementById('nasdaq').innerHTML;
    document.getElementById('nasdaq').innerHTML = old_string.replace(old_string, "The price of gold is " + gold_price);
    });
});

function convert_weight(){
var day = new Date()
var time = day.getHours() + ":" + day.getMinutes() + ":" + day.getSeconds()
var from = document.querySelector('#unitOfWeight');
var value = document.querySelector('#weight');
    from_var = from.value;
    from_val = parseFloat(value.value);


if (isNaN(from_val) || from_val <= 0){
    let new_div = document.createElement('div');
        new_div.setAttribute('class', 'stuff-box black');
        new_div.addEventListener('click', () => {new_div.remove()});

    let invalid_input = document.createElement('p')
        invalid_input.setAttribute('class', 'stuff-box cyan')
        invalid_input.textContent = 'Please input a valid positive number';
        new_div.appendChild(invalid_input)

    let elements = document.getElementsByClassName('black')
    let firstDiv = elements[0]

    document.body.insertBefore(new_div, firstDiv)
}
else{
    fetch(`http://localhost:8000/unitconv/convert?from=${from_var}&to=t_oz&value=${from_val}`)
     .then(response => {
    return response.json();
    })
    .then(json => {
    if (json.hasOwnProperty('error')) {
        throw json.error;
    }
    result = json.value * gold_price;
    units = json.units;

    let convert_div = document.createElement('div');
        convert_div.setAttribute('class', 'stuff-box black');
        convert_div.addEventListener('click', () => {convert_div.remove()});

    let conversion = document.createElement('p');
        conversion.setAttribute('class', 'stuff-box green')
        conversion.textContent = `The weight is worth \$${result.toFixed(2)} in gold at ${time}`;
        convert_div.appendChild(conversion);

    let elements = document.getElementsByClassName('black')
    let firstDiv = elements[0]

    document.body.insertBefore(convert_div, firstDiv)
    })
    .catch(err => {
    let error_div = document.createElement('div');
        error_div.setAttribute('class', 'stuff-box black');
        error_div.addEventListener('click', () => {error_div.remove()});

    let err_message = document.createElement('p');
        err_message.setAttribute('class', 'stuff-box red')
        err_message.textContent = "Something Went Wrong";
        error_div.appendChild(err_message);

    let elements = document.getElementsByClassName('black')
    let firstDiv = elements[0]

    document.body.insertBefore(error_div, firstDiv)
    })
}
}
