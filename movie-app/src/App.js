import React, { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import MovieList from './components/MovieList';
import MovieListHeading from './components/MovieListHeading';
import SearchBox from './components/SearchBox';
import AddFaves from './components/AddFaves';
import RemoveFaves from './components/RemoveFaves';
const App = () => {
    const [movies, setMovies] = useState([]);
    const [faves, setFaves] = useState([]);
    const [searchValue, setSearchValue] = useState('');
   
const getMovieRequest = async (searchValue) => {
    const url = `http://www.omdbapi.com/?s=${searchValue}&apikey=531d81d`;

    const response = await fetch(url);
    const responseJson = await response.json();

    if (responseJson.Search){
        setMovies(responseJson.Search);
    }
    
};

useEffect(() => {
    getMovieRequest(searchValue);
}, [searchValue]);

useEffect(() => {
   const movieFaves = JSON.parse(
       localStorage.getItem('react-movie-app-faves')
   ); 
   if (movieFaves){
    setFaves(movieFaves);
   }
   
}, []);

const saveToLocalStorage = (items) => {
    localStorage.setItem('react-movie-app-faves', JSON.stringify(items));
};

const addFaveMovie = (movie) => {
    const newFaveList = [...faves, movie];
    setFaves(newFaveList);
    saveToLocalStorage(newFaveList);
};

const removeFaveMovie = (movie) => {
    const newFaveList = faves.filter(
        (fave) => fave.imdbID !== movie.imdbID
    );

    setFaves(newFaveList);
    saveToLocalStorage(newFaveList);
};
return (
    <div className = 'container-fluid movie-app'>
        <div className = 'row d-flex align-items-center mt-4 mb-4'>
            <MovieListHeading heading = 'Movies' />
            <SearchBox searchValue = {searchValue} setSearchValue = {setSearchValue} />
        </div>
        <div className = 'row'>
            <MovieList 
                movies = {movies} 
                handleFavesClick = {addFaveMovie} 
                faveComponent = {AddFaves} 
            />
        </div>
        <div className = 'row d-flex align-items-center mt-4 mb-4'>
            <MovieListHeading heading = 'Favorites' />
        </div>
        <div className = 'row'>
            <MovieList 
                movies = {faves} 
                handleFavesClick = {removeFaveMovie} 
                faveComponent = {RemoveFaves} 
            />
        </div>    
    </div>
    );
};

export default App;