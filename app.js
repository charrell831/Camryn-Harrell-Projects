document.addEventListener('DOMContentLoaded', () => {
    const gridDisplay = document.querySelector('.grid')
    const scoreDisplay = document.getElementById('score')
    const resultDisplay = document.getElementById('result')
    const width = 4
    let squares = []
    let score = 0
    //function to create the starting board
    function createBoard(){
        //as long as i is less than 16, keep looping
        for (let i = 0; i < width*width; i++){
            square = document.createElement('div') //created element called sqaure with a node called div
            square.innerHTML = 0 //sets number inside of square to 0
            gridDisplay.appendChild(square) //append to grid
            squares.push(square) //add to an array of squares     
        }
        //to generate 2 random 2s
        generate()
        generate()
    }
    createBoard()

    //function to generate random numbers
    function generate(){
        //for random place in grid
      let randomNumber = Math.floor(Math.random() * squares.length)  
      //if random spot in grid is 0, means we could start off that postion w/ a 2
      if (squares[randomNumber].innerHTML == 0) {
          squares[randomNumber].innerHTML = 2;
          checkForGameOver()
      } else generate() //if there is a number other than 2, generate at another place?
      //else statement prevents the 2s from overlapping
    }

    //moves randomized numbers to the right
    function moveRight(){
        for (let i = 0; i < 16; i++){
            if (i % 4 === 0){ //to define rows
                //if if statement applies, means we are at the beginning of a row
                let totalOne = squares[i].innerHTML
                let totalTwo = squares[i+1].innerHTML
                let totalThree = squares[i+2].innerHTML
                let totalFour = squares[i+3].innerHTML
                //innerHTML is currently a string so we must use parseInt to convert
                //makes an array to hold totals of row
                let row = [parseInt(totalOne), parseInt(totalTwo), parseInt(totalThree), parseInt(totalFour)]

    
                let filteredRow = row.filter(num => num) //filter out any elements that has a number besides 0 in row array
              
                let missing = 4 - filteredRow.length
                //# of numbers that havent been filled
                let zeros = Array(missing).fill(0) //filling any missing numbers with 0
          
                //attach zeros array to filter array in order
                //so that filtered numbers appear on right side of screen
                let newRow = zeros.concat(filteredRow)
           

                //replace actual squares in the game with elements from newRow
                squares[i].innerHTML = newRow[0]
                squares[i+1].innerHTML = newRow[1]
                squares[i+2].innerHTML = newRow[2]
                squares[i+3].innerHTML = newRow[3]
                
    
            }
        }
    }

 

   //moves randomized numbers to the right
    function moveLeft(){
        for (let i = 0; i < 16; i++){
            if (i % 4 === 0){ //to define rows
                //if if statement applies, means we are at the beginning of a row
                let totalOne = squares[i].innerHTML
                let totalTwo = squares[i+1].innerHTML
                let totalThree = squares[i+2].innerHTML
                let totalFour = squares[i+3].innerHTML
                //innerHTML is currently a string so we must use parseInt to convert
                //makes an array to hold totals of row
                let row = [parseInt(totalOne), parseInt(totalTwo), parseInt(totalThree), parseInt(totalFour)]

                let filteredRow = row.filter(num => num) //filter out any elements that has a number besides 0 in row array
          
                let missing = 4 - filteredRow.length
                //# of numbers that havent been filled
                let zeros = Array(missing).fill(0) //filling any missing numbers with 0
          
                //attach zeros array to filter array in order
                //so that filtered numbers appear on right side of screen
                let newRow = filteredRow.concat(zeros)
                //move filtered numbers infront of zeros instead of behind like in moveRight function

                //replace actual squares in the game with elements from newRow
                squares[i].innerHTML = newRow[0]
                squares[i+1].innerHTML = newRow[1]
                squares[i+2].innerHTML = newRow[2]
                squares[i+3].innerHTML = newRow[3]
                
    
            }
        }
    }

    //swipe down
    function moveDown(){
        for (let i = 0; i < 4; i++){
            let totalOne = squares[i].innerHTML
            let totalTwo = squares[i+width].innerHTML
            let totalThree = squares[i+width*2].innerHTML
            let totalFour = squares[i+width*3].innerHTML
            let column = [parseInt(totalOne), parseInt(totalTwo), parseInt(totalThree), parseInt(totalFour)]

            let filteredColumn = column.filter(num => num)
            let missing = 4 - filteredColumn.length
            let zeros = Array(missing).fill(0)
            let newColumn = zeros.concat(filteredColumn)

            squares[i].innerHTML = newColumn[0]
            squares[i+width].innerHTML = newColumn[1]
            squares[i+width*2].innerHTML = newColumn[2]
            squares[i+width*3].innerHTML = newColumn[3]
        }
    }

      //swipe up
      function moveUp(){
        for (let i = 0; i < 4; i++){
            let totalOne = squares[i].innerHTML
            let totalTwo = squares[i+width].innerHTML
            let totalThree = squares[i+width*2].innerHTML
            let totalFour = squares[i+width*3].innerHTML
            let column = [parseInt(totalOne), parseInt(totalTwo), parseInt(totalThree), parseInt(totalFour)]

            let filteredColumn = column.filter(num => num)
            let missing = 4 - filteredColumn.length
            let zeros = Array(missing).fill(0)
            let newColumn = filteredColumn.concat(zeros)

            squares[i].innerHTML = newColumn[0]
            squares[i+width].innerHTML = newColumn[1]
            squares[i+width*2].innerHTML = newColumn[2]
            squares[i+width*3].innerHTML = newColumn[3]
        }
    }

    function combineRow() {
        for (let i = 0; i < 15; i++){
            if (squares[i].innerHTML === squares[i+1].innerHTML){
                //means two number next to eachother are the same and can be combined
                let combinedTotal = parseInt(squares[i].innerHTML) + parseInt(squares[i+1].innerHTML)
                squares[i].innerHTML = combinedTotal
                squares[i+1].innerHTML = 0
                score+= combinedTotal
                scoreDisplay.innerHTML = score
            }
        }
        checkForWin()
    }

    function combineColumn() {
        for (let i = 0; i < 12; i++){
            if (squares[i].innerHTML === squares[i+width].innerHTML){
                //means two number next to eachother are the same and can be combined
                let combinedTotal = parseInt(squares[i].innerHTML) + parseInt(squares[i+width].innerHTML)
                squares[i].innerHTML = combinedTotal
                squares[i+width].innerHTML = 0
                score+= combinedTotal
                scoreDisplay.innerHTML = score
            }
        }
        checkForWin()
    }


    //assign keycodes
    //when using keyboard we can invoke functions
    function control(e) {
        if (e.keyCode === 39){  //code of key on key board = 39
            keyRight() //calls move right function when moving right
        } else if (e.keyCode === 37){
            keyLeft()
        } else if (e.keyCode === 38) {
            keyUp()
        } else if (e.keyCode === 40){
            keyDown()
        }
    }

    //listen for when a key is being used and call control function as a result
    document.addEventListener('keyup', control)

    function keyRight() {
        //when swiping right, move keys to right
        moveRight()
        //combine keys
        combineRow()
        //move nonzeros to front
        moveRight()
        //generate 2 new random numbers in random areas of the grid
        generate()
    }

    function keyLeft(){
        moveLeft()
        combineRow()
        moveLeft()
        generate()
    }

    function keyDown(){
        moveDown()
        combineColumn()
        moveDown()
        generate()
    }

    function keyUp(){
        moveUp()
        combineColumn()
        moveUp()
        generate()
    }

    //check for the number 2048 in all squares
    function checkForWin(){
        for (let i = 0; i < squares.length; i++){
            if (squares[i].innerHTML == 2048){
                resultDisplay.innerHTML = 'You Win!'
                document.removeEventListener('keyup', control)
            }
        }
    }

    //check if there are no zeros in function (no more open spaces)
    //game over function
    function checkForGameOver(){
        let zeros = 0
        for (let i = 0; i < squares.length; i++){
            if (squares[i].innerHTML == 0){
                zeros++
            }
        }
        if (zeros === 0){
            resultDisplay.innerHTML = 'Game Over: Refresh the Page to Try Again!'
            //remove key usage
            document.removeEventListener('keyup', control)
        }
    }
})