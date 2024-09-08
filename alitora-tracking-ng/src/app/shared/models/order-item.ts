export interface OrderItem{
  orderCode?: string;
  itemCode: string;
  quantity: number;
  totalPrice: number;
  name?: string;
  unitPrice?: string;
}
