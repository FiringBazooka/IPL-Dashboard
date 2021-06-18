
import TeamPage from "./components/TeamPage";
import "./App.css";
import {BrowserRouter as Router, Link, Route} from 'react-router-dom';
import MatchPage from "./components/MatchPage";
import HomePage from "./components/HomePage";


function App() {
  return (
  
    <div className='App'>
    

      <Router>

      <div className='home-title-div'>
      <h1> 
       <Link className='home-title-link' to='/'>IPL Dashboard </Link> 
      </h1>
      </div>

        {/* Allow any URL with this URI format /teams/Foo */}

        <Route path='/team/:teamName/matches/:year' exact>
          <MatchPage/>
        </Route>

        <Route path='/team/:teamName' exact>
          <TeamPage/>
        </Route>

        <Route path='/' exact>
          <HomePage/>
        </Route>
      
      </Router>
	
    </div>
    
  );
}

export default App;
