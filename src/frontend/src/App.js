
import TeamPage from "./components/TeamPage";
import "./App.css";
import {BrowserRouter as Router, Route} from 'react-router-dom';


function App() {
  return (
  
    <div className='App'>
    	<h1> IPL Dashboard </h1>
      <Router>
        {/* Allow any URL with this URI format /teams/Foo */}
        <Route path='/team/:teamName'>
        <TeamPage/>
      </Route>
      </Router>
	
    </div>
    
  );
}

export default App;
