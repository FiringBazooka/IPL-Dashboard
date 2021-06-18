import { useEffect, useState } from "react";
import HomeTile from "./HomeTile";
import './HomePage.scss'


const HomePage = () => {

    const[teams, setTeams]=useState([{}]);

    useEffect(  
        () => {
            const serviceCall = async () => {
              const response =  await fetch('http://localhost:8080/teams');
              const data = await response.json();
              setTeams(data);
              console.log(data)
              
            }
            serviceCall();
            
        },[]
    );

    return (
        <div className='HomePage'>
            {teams.map(e=> <HomeTile teamName={e.teamName}/>)}
        </div>
    )
}

export default HomePage;