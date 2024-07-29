package com.elintefaz.El_intefaz.Integration;
import com.elintefaz.El_intefaz.model.Order;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class EmailIntegration {
    @Value("${email.key}")
    private String key;
   private final String SUBJET="ElInterfaz: Notificación de Orden de Compra";


    public void sendEmail(Order order) throws ResendException {
        Resend resend = new Resend(this.key);
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(order.getEmail())
                .subject(SUBJET)
                .html(generateHTML(order))
                .build();
        CreateEmailResponse  data = resend.emails().send(params);
    }


    private String generateHTML(Order order){
        return "<!DOCTYPE html>"
                + "<html lang=\"es\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "    <style>"
                + "        body {"
                + "            font-family: Arial, sans-serif;"
                + "            margin: 0;"
                + "            padding: 20px;"
                + "            background-color: #f4f4f9;"
                + "        }"
                + "        .container {"
                + "            max-width: 600px;"
                + "            margin: auto;"
                + "            background-color: #ffffff;"
                + "            padding: 20px;"
                + "            border-radius: 8px;"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
                + "        }"
                + "        h1 {"
                + "            color: #333333;"
                + "            text-align: center;"
                + "        }"
                + "        p {"
                + "            line-height: 1.6;"
                + "            color: #666666;"
                + "        }"
                + "        .order-id {"
                + "            font-weight: bold;"
                + "            color: #007bff;"
                + "        }"
                + "        .footer {"
                + "            text-align: center;"
                + "            margin-top: 20px;"
                + "            font-size: 0.9em;"
                + "            color: #888888;"
                + "        }"
                + "    </style>"
                + "</head>"
                + "<body>"
                + "    <div class=\"container\">"
                + "        <h1>Orden de Compra Creada</h1>"
                + "        <p>Hola,</p>"
                + "        <p>Nos complace informarte que tu orden de compra ha sido creada con éxito. Aquí están los detalles:</p>"
                + "        <p><strong>ID de la Orden: </strong> <span class=\"order-id\">" +order.getIdOrder()
                + "</span></p>"
                + "        <p>Gracias por confiar en nosotros. Si tienes alguna pregunta, no dudes en contactarnos.</p>"
                + "        <div class=\"footer\">"
                + "            <p>Atentamente,</p>"
                + "            <p>ElInterfaz</p>"
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }
}
