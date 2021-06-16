
import TeamPage from "./components/TeamPage";
import "./App.css";
import {BrowserRouter as Router, Route} from 'react-router-dom';
import MatchPage from "./components/MatchPage";


function App() {
  return (
  
    <div className='App'>
    	<h1> IPL Dashboard </h1>
      <Router>
        {/* Allow any URL with this URI format /teams/Foo */}

        <Route path='/team/:teamName/matches/:year' exact>
          <MatchPage/>
        </Route>

        <Route path='/team/:teamName' exact>
          <TeamPage/>
        </Route>
      
      </Router>
	
    </div>
    
  );
}

export default App;
