select 
    strftime("%d/%m/%Y", l.createdAt), 
    i.produto, 
    1,
    case when i.valor > 0 then
        case l.formaPagamento
            when "debito" then 
                i.valor
            when "credito" then
                i.valor
            else
                ""
        end
    end,
    case when i.valor > 0 and l.formaPagamento <> "debito" and l.formaPagamento <> "credito" then
	    i.valor
    else
        ""
    end,
    case when i.valor < 0 or l.formaPagamento = "pagamentoOnline" then
      abs(i.valor)
    else
      ""
    end

from lancamentos l 
join items i 
    on l.id = i.lancamento_id 
where
    strftime('%Y%m', l.createdAt) = '201402'
order by l.createdAt;
