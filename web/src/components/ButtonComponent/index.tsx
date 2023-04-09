import './styles.scss';
import { ButtonType } from '../../types/ButtonType';

export default function ButtonComponent({
    buttonId,
    className,
    onClick,
    text,
    size
}: ButtonType) {
    return (
        <button
            id={buttonId}
            className={`${className} ${size}`}
            onClick={onClick}
        >
            {text}
        </button>
    );
}