import { useState } from 'react'
import './styles.scss';
import registerImg from '../../assets/register-image.svg'
import { useNavigate } from 'react-router-dom';
import { HiOutlineMail } from 'react-icons/hi'
import { AiOutlineEye, AiOutlineEyeInvisible, AiOutlineUser } from 'react-icons/ai'
import { BiLockAlt } from 'react-icons/bi'
import InputField from '../../components/InputField';

export default function RegisterUser() {
    const [showPassword, setShowPassword] = useState(true);

    return (
        <div className='register-user-container'>
            <form>
                <div className='register-user-aside'>
                    <header>
                        <h2>Cadastre-se agora na <span>eco</span>.</h2>
                    </header>

                    <InputField
                        inputId='name-id'
                        label='Nome Completo'
                        placeholder='Preencha com o seu nome completo'
                        type='text'
                    />

                    <div className="email-field">
                        <label htmlFor="email-id">E-mail</label>

                        <div className="email-input">
                            <HiOutlineMail className='icon' />
                            <input
                                type="text"
                                id="email-id"
                            />
                        </div>
                    </div>

                    <div className="password-field">
                        <label htmlFor="password-id">Senha</label>

                        <div className="password-input">
                            <BiLockAlt className='icon' />
                            <input
                                type="text"
                                id="password-id"
                            />

                            {showPassword ?
                                <span className='showEyeIcon'>
                                    <AiOutlineEyeInvisible />
                                </span>
                                :
                                <span className='showEyeIcon'>
                                    <AiOutlineEye />
                                </span>
                            }
                        </div>
                    </div>

                    <button>Cadastrar</button>
                </div>

                <aside>
                    <img src={registerImg} alt="Register user image" />
                </aside>
            </form>
        </div>
    );
}