select 
    strftime("%d/%m/%Y", l.createdAt), 
    i.produto, 
    i.quantidade,
    case when i.valor > 0 then
        case l.formaPagamento
            when "debito" then 
                (i.valor * i.quantidade)
            when "credito" then
                (i.valor * i.quantidade)
            else
                ""
        end
    end,
    case when i.valor > 0 and l.formaPagamento <> "debito" and l.formaPagamento <> "credito" then
	    (i.valor * i.quantidade)
    else
        ""
    end,
    case when i.valor < 0 or l.formaPagamento = "pagamentoOnline" then
      abs(i.valor * i.quantidade)
    else
      ""
    end
from lancamentos l 
join items i 
    on l.id = i.lancamento_id 
where
    strftime('%Y-%m', l.createdAt) = ?
order by l.createdAt;
