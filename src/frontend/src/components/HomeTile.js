import { Link } from 'react-router-dom'
import './HomeTile.scss'

const HomeTile = ({teamName}) => {

    return (
        <Link className='link-team' to={`/team/${teamName}`}>
        <div className='HomeTile'>
            {teamName}
        </div>
        </Link>
    )
}

export default HomeTile;