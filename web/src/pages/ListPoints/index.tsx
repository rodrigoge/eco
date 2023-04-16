import { Link } from 'react-router-dom';
import ButtonComponent from '../../components/ButtonComponent';
import HeaderComponent from '../../components/HeaderComponent';
import './styles.scss';
import { AiOutlineArrowLeft } from 'react-icons/ai';
import pointApi from '../../services/pointApi';
import { useState, useEffect } from 'react';
import { PointType } from '../../interface/PointInterface';

export default function ListPoints() {
    const [points, setPoints] = useState<PointType[]>([]);

    useEffect(() => {
        fetchPoints();
        console.log('passou')
    });

    async function fetchPoints() {
        try {
            const response = await pointApi.get('/v1/points/list', {
                params: {
                    offset: 0,
                    limit: 25,
                    order: 'ASC',
                    sort: 'name'
                }
            });

            if (response) {
                setPoints(response.data.points);
            }
        } catch (err) {
            setPoints([]);
            return;
        }
    }

    return (
        <div className="points-container">
            <HeaderComponent />

            <div className="ecopoints">
                <header>
                    <h2>Lista de Ecopontos disponíveis</h2>

                    <Link to={"/"} className='back-page'>
                        <AiOutlineArrowLeft />
                        <span>Voltar</span>
                    </Link>
                </header>

                <div className="cards-in-row">
                    {points.map((point, key) => (
                        <div key={key} className="cards-container">
                            <div className="card">
                                <h3>{point.name}</h3>

                                <div className="ecopoint-content">
                                    <span>{point.address}</span>
                                    <span>{point.city} - {point.uf}</span>
                                    <span>{point.initialHour}h às {point.endHour}h</span>
                                </div>

                                <div className="ecopoint-buttons">
                                    <ButtonComponent
                                        className='btn btn-blue'
                                        size='btn-sm'
                                        text='Cadastrar'
                                    />
                                    <ButtonComponent
                                        className='btn btn-green'
                                        size='btn-sm'
                                        text='Descartar'
                                    />
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}