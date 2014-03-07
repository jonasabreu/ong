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
    case when i.valor > 0 then
        case l.formaPagamento
            when "dinheiro" then 
                i.valor
            else
                ""
        end
    end,
    case when i.valor < 0 then
      abs(i.valor)
    else
      ""
    end

from lancamentos l 
join items i 
    on l.id = i.lancamento_id 
where
    strftime('%m', l.createdAt) = '02' and
    strftime('%Y', l.createdAt) = '2014'
order by l.createdAt;
