import { useParams} from 'react-router-dom';
import { useContextGlobal } from "../Components/utils/GlobalContext";
import { useEffect, useState } from 'react';
import Gallery from '../Components/Gallery';

const Detail = () => {

  const images = ["/public/images/clubTenis.jpg", "/public/images/canchaFutbol7.png", "/public/images/canchaPadel.png", "/public/images/canchaBasket.png"]

    const {id} = useParams()
    const {data} = useContextGlobal()

    useEffect(() =>{
      window.scrollTo(0,0)
    },[])

  return (
    <div className='detailGeneralDiv'>
       <div className='detailMidDiv'>
        <h2>{data[id-1]?.name}</h2>
          <div>
             <img src="/images/location.png" alt="" />
             <p>{data[id-1]?.location}</p>
          </div>
      </div>
      <div className='blockImage'>
        <div className='leftBlock'>
            <img src={data[id-1]?.image} alt="" />
        </div>
        <div className='rightBlock'>
          <div className='semiBlock'>
            <img src={data[id-1]?.image} alt="" />
            <img src={data[id-1]?.image} alt="" />
          </div>
          <div className='semiBlock'>
            <img src={data[id-1]?.image} alt="" />
            <img src={data[id-1]?.image} alt="" />
              <Gallery images={images}/>
          </div>
        </div>
      </div>
      <div className='detailBottomDiv'>
        <div className='divLocation'>
         <h2>Donde podes encontrarnos</h2>
         <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3284.052866542891!2d-58.37652822334277!3d-3
         4.60282465745262!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95bccacd02e46f1f%3A0x70f27b4c4725a03b!2sSan%20Mart%C3%AD
         n%20444%2C%20C1004%20AAJ%2C%20Buenos%20Aires!5e0!3m2!1ses!2sar!4v1684863236125!5m2!1ses!2sar" ></iframe>
        </div>
        <div className='divInfo'>
          <div>
            <h2>Que servicios ofrecemos</h2>
            <hr />
            <p>{data[id-1]?.services}</p>
          </div>
        </div>
      </div>
    </div>
  
  )
}

export default Detail