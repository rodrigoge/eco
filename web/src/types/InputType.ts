import { ReactNode } from "react";
import { IconBaseProps, IconType } from "react-icons/lib";

export type InputType = {
    inputId?: string;
    placeholder?: string;
    label?: string;
    type?: string;
    icon?: ReactNode;
    isPassword?: boolean;
    value?: string;
    onChange?: React.ChangeEventHandler<HTMLInputElement>;
    maxLength?: number;
}